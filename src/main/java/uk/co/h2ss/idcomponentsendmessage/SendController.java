package uk.co.h2ss.idcomponentsendmessage;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uk.co.h2ss.msg.Message;

@Controller
public class SendController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
		
	AtomicLong al= new AtomicLong(0);
	    	
    @GetMapping("/sendmessage")
    public String messageForm(Model model) {
    	Message message = new Message();
    	message.setId(al.incrementAndGet());
        model.addAttribute("sendmessage", message);
        return "sendmessage";
    }

    @PostMapping("/sendmessage")
    public String messageSubmit(@ModelAttribute Message message) {
        jmsTemplate.convertAndSend("id2asset", message); 	
        return "sentmessage";
    }
}
