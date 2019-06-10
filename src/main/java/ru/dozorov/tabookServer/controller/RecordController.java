package ru.dozorov.tabookServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.dozorov.tabookServer.domain.Record;
import ru.dozorov.tabookServer.repo.RecordRepo;
import ru.dozorov.tabookServer.security.JwtTokenProvider;
import ru.dozorov.tabookServer.service.RecordService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {
    private final RecordService service;

    @Autowired
    public RecordController(RecordService service) {
        this.service = service;
    }

    //получение всех записей
    //ПОТОМ УБРАТЬ!!!!!!!!!!!!!!!!!!!!!!!!!!
    @GetMapping()
    public List<Record> getRecords() {
        return recordRepo.findAll();
    }

    //получение записей пользователя
    @GetMapping("{token}")
    public List<Record> getRecords(@PathVariable("token") String token) {
//        List<Record> o = service.getRecords(token);
//        if (o != null){
//            return
//        }
//        List<Long> longs = new ArrayList<>();
//        longs.add(Long.getLong(userId));
//
//        return recordRepo.findAllById(longs);
        return service.getRecords(token);
    }

    //добавление
    @PostMapping("{token}")
    public Record addRecord(
            @PathVariable String token,
            @RequestBody Record record) {
//        record.setDate(LocalDateTime.now());
//        record.setUserId(Long.getLong(UserId));
        return recordRepo.save(record);
    }

    //обновление
    @PutMapping("{token}/{id}")
    public Record updRecord(@PathVariable("id") long id,
                            @PathVariable("token") long userId,
                            @RequestBody Record record) {
        Record recFromDB = null;
//        Example<Record> example = Example.of(new Record(id, new User())); //создание примера
//        if (recordRepo.findOne(example).isPresent()) { //найдена ли подходящая запись?
//            recFromDB = recordRepo.findOne(example).get(); //поиск по примеру записи Record с указанным id и UserId
//            BeanUtils.copyProperties(record, recFromDB, "id");//копируем из старой в новую
//            return recordRepo.save(recFromDB);//возвращаем новую запись
//        }
        return null;//иначе не возвращаем ничего
    }

    //удаление
    @DeleteMapping("{token}/{id}")
    public void delRecord(@PathVariable("id") long id,
                          @PathVariable("userId") long userId) {
//        Example<Record> example = Example.of(new Record(id, new User()));
//        if (recordRepo.findOne(example).isPresent())
//        {
//            recordRepo.delete(recordRepo.findOne(example).get());
//        }
    }

    //количество записей?
//    @RequestMapping("nor")
//    @GetMapping("{userId}")
//    public Long getnumbers(@PathVariable("userId") String uid){
//        return recordRepo.count(Example.of(new Record(new User())));
//    };

}
