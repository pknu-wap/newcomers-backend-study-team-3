package com.example.board.Service;

import com.example.board.Entity.Thread;
import com.example.board.Repository.ThreadRepository;
import jakarta.annotation.PostConstruct;

public class ThreadService {
    ThreadRepository threadRepository;

    @PostConstruct
    public void createThread(Thread thread) {
//        Thread thread = new Thread();
//        thread.setId(1L);
//        thread.title="제목";
//        thread.content="내용";
//        thread.createdAt= LocalDateTime.now();
        threadRepository.save(thread);
    }

    public void readThread() {
    }

    public void updateThread() {
    }

    public void deleteThread() {
    }
}
