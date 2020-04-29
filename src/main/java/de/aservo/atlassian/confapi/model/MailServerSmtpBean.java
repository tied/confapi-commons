package de.aservo.atlassian.confapi.model;

import com.atlassian.mail.server.SMTPMailServer;
import de.aservo.atlassian.confapi.constants.ConfAPI;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = ConfAPI.MAIL_SERVER + "-" + ConfAPI.MAIL_SERVER_SMTP)
public class MailServerSmtpBean extends AbstractMailServerProtocolBean {

    @XmlElement
    private String adminContact;

    @XmlElement
    private String from;

    @XmlElement
    private String prefix;

    @XmlElement
    private boolean tls;

    /**
     * Constructor for {@link MailServerSmtpBean} used in crowd-confapi-plugin.
     *
     * @param adminContact the admin contact email address
     * @param from         the server from email address
     * @param prefix       the subject prefix
     * @param host         the SMTP host
     */
    public MailServerSmtpBean(
            final String adminContact,
            final String from,
            final String prefix,
            final String host) {

        setAdminContact(adminContact);
        setFrom(from);
        setPrefix(prefix);
        setHost(host);
    }

    @Nullable
    public static MailServerSmtpBean from(
            @Nullable final SMTPMailServer smtpMailServer) {

        if (smtpMailServer == null) {
            return null;
        }

        final MailServerSmtpBean mailServerSmtpBean = new MailServerSmtpBean();
        mailServerSmtpBean.setName(smtpMailServer.getName());
        mailServerSmtpBean.setDescription(smtpMailServer.getDescription());
        mailServerSmtpBean.setFrom(smtpMailServer.getDefaultFrom());
        mailServerSmtpBean.setPrefix(smtpMailServer.getPrefix());
        mailServerSmtpBean.setProtocol(smtpMailServer.getMailProtocol().getProtocol());
        mailServerSmtpBean.setHost(smtpMailServer.getHostname());
        mailServerSmtpBean.setPort(smtpMailServer.getPort());
        mailServerSmtpBean.setTls(smtpMailServer.isTlsRequired());
        mailServerSmtpBean.setTimeout(smtpMailServer.getTimeout());
        mailServerSmtpBean.setUsername(smtpMailServer.getUsername());
        return mailServerSmtpBean;
    }

}
