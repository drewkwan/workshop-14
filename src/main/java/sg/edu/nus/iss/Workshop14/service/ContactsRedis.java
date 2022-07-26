package sg.edu.nus.iss.Workshop14.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.Workshop14.model.Contact;



@Service
public class ContactsRedis implements ContactsRepo { //why did you need to implement the contacts repo and is it possible to do this within the same class?
    private static final Logger logger = LoggerFactory.getLogger(ContactsRedis.class);

    @Autowired
    RedisTemplate<String, Object> redisTemplate; //what is redis template?

    @Override 
    public void save(final Contact ctc) {
        redisTemplate.opsForValue().set(ctc.getId(), ctc);
    }

    @Override
    public Contact findById(final String contactId) {
        Contact result = (Contact) redisTemplate.opsForValue().get(contactId);
        logger.info(">>> " + result.getEmail());
        return result;
    }
}
    
