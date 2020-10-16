package com.wisenut.infrastructures.mail;

import com.mysema.commons.lang.Assert;
import com.wisenut.domain.common.mail.Mailer;
import com.wisenut.domain.common.mail.Message;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;



@Log4j2
@Component
public class AsyncMailer implements Mailer {

    private JavaMailSender mailSender;

    public AsyncMailer(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void send(Message message) {
        Assert.notNull(message, "파라미터 `message`는 null 값일 수 없습니다.");

        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            if(StringUtils.isNotBlank(message.getFrom())){
                mailMessage.setFrom(message.getFrom());
            }

            if (StringUtils.isNotBlank(message.getSubject())) {
                mailMessage.setSubject(message.getSubject());
            }
            if (StringUtils.isNotEmpty(message.getBody())) {
                mailMessage.setText(message.getBody());
            }
            if (message.getTo() != null) {
                mailMessage.setTo(message.getTo());
            }

            mailSender.send(mailMessage);
        }catch (MailException e){
            log.error("메일 보내는데 실패함..."+e);
        }
    }
}
