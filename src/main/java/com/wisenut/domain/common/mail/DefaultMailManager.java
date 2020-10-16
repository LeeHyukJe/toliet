package com.wisenut.domain.common.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;


import java.util.HashMap;
import java.util.Map;

@Log4j2
@Component
public class DefaultMailManager implements MailManager{
    private String mailFrom;
    private Mailer mailer;
    private Configuration configuration;

    public DefaultMailManager(@Value("${app.mail-from}") String mailFrom,
                              Mailer mailer,
                              @Qualifier("getFreemarkerConfiguration") Configuration configuration) {
        this.mailFrom = mailFrom;
        this.mailer = mailer;
        this.configuration = configuration;
    }
    @Override
    public void send(String emailAddress, String subject, String template, MessageVariable... variables) {
        Assert.hasText(emailAddress,"파라미터 `emailAddress`는 빈칸이어서는 안 됩니다.");
        Assert.hasText(subject, "파라미터 `subject`는 빈칸이어서는 안 됩니다.");
        Assert.hasText(template, "파라미터 `template`는 빈칸이어서는 안 됩니다.");

        String messageBody = createMessageBody(template, variables);
        Message message = new SimpleMessage(emailAddress, subject, messageBody, mailFrom);
        mailer.send(message);
    }

    private String createMessageBody(String templateName, MessageVariable... variables){
        try{
            Template template = configuration.getTemplate(templateName);
            Map<String,Object> model = new HashMap<>();
            if(variables != null){
                for(MessageVariable variable: variables){
                    model.put(variable.getKey(), variable.getValue());
                }
            }

            return FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
        }catch (Exception e){
            log.error("템플릿으로부터 메시지 본문을 생성하는데 실패함..."+"`"+templateName+"`", e);
            return null;
        }
    }
}
