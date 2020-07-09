package org.nextrtc.signalingserver.domain;

import org.nextrtc.signalingserver.repository.MemberRepository;

import com.google.gson.Gson;

public class PingTask implements Runnable {
	private static Gson gson = new Gson();
    private MessageSender sender;
    private Member to;
    private MemberRepository members;
    public PingTask(Connection to, MessageSender sender,MemberRepository members) {
        this.to = new Member(to, null);
        this.sender = sender;
        this.members = members;
    }

    @Override
    public void run() {
        if(Thread.interrupted()){
            return;
        }
        sender.send(InternalMessage.create()//
                .to(to)//
                .signal(Signal.PING)//
                .content(gson.toJson(members.getAllIds()))
                .build()//
        );
    }

}
