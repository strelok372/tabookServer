package ru.dozorov.tabookServer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dozorov.tabookServer.domain.Record;
import ru.dozorov.tabookServer.repo.RecordRepo;
import ru.dozorov.tabookServer.security.JwtTokenProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class RecordService {
    private final RecordRepo recordRepo;
    private final JwtTokenProvider tokenProvider;

    @Autowired
    public RecordService(RecordRepo recordRepo, JwtTokenProvider tokenProvider) {
        this.recordRepo = recordRepo;
        this.tokenProvider = tokenProvider;
    }

    public List<Record> getRecords(String token){
        String username;
        if (tokenProvider.validateToken(token)) {
            username = tokenProvider.getUsername(token);
            return (List<Record>)recordRepo.findRecordsByUser(username);
        }else {
         return null;
        }
    }

    public void addRecord(String token, Record record){
        String username;
        if (tokenProvider.validateToken(token)) {
            username = tokenProvider.getUsername(token);

        }

    }

    public void updRecord(){

    }

    public void delRecord(){

    }


}
