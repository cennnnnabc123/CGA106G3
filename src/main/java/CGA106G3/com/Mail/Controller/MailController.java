package CGA106G3.com.Mail.Controller;

import CGA106G3.com.Mail.DTO.ForgetDTO;
import CGA106G3.com.Mail.DTO.MailDTO;
import CGA106G3.com.Mail.Service.MailServiceImpl;
import CGA106G3.com.member.Entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class MailController {
    @Autowired
    private MailServiceImpl mailServiceImpl;

    @PostMapping("/sendMail")
    public void SendEmail(@RequestBody MailDTO mailDTO){
        mailServiceImpl.sendEmail(mailDTO);
    }
    @PostMapping("/findPassword")
    public boolean findPassword(@RequestBody ForgetDTO email){
       return mailServiceImpl.sendPassword(email);
    }
}
