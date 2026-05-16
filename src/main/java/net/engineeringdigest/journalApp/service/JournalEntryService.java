package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.Users;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.channels.ScatteringByteChannel;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService

{
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName)
    {
        try{
        Users user = userService.findByUsername(userName);
            journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("error",e);
        }


    }
    public void saveEntry(JournalEntry journalEntry)
    {

        journalEntryRepository.save(journalEntry);

    }
    public List<JournalEntry> findAll()
    {

        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id)
    {

        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean   deleteById(ObjectId id, String username)
    {
        boolean b=false;
        try {
            Users user = userService.findByUsername(username);
             b = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (b) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("error",e);
        }
        return b;
    }
}
