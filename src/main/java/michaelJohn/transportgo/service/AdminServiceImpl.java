package michaelJohn.transportgo.service;

import lombok.AllArgsConstructor;
import michaelJohn.transportgo.data.dtos.request.EmailNotificationRequest;
import michaelJohn.transportgo.data.dtos.request.InviteAdminRequest;
import michaelJohn.transportgo.data.dtos.request.Recipient;
import michaelJohn.transportgo.data.dtos.response.ApiResponse;
import michaelJohn.transportgo.data.models.Admin;
import michaelJohn.transportgo.data.models.AppUser;
import michaelJohn.transportgo.data.repositories.AdminRepository;
import michaelJohn.transportgo.exception.BusinessLogicException;
import michaelJohn.transportgo.notification.MailService;
import michaelJohn.transportgo.util.AppUtilities;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final MailService mailService;

    public ApiResponse sendInviteRequests(Set<InviteAdminRequest> inviteAdminRequestList) {
        EmailNotificationRequest request = new EmailNotificationRequest();
        var recipients = inviteAdminRequestList.stream()
                .map(inviteAdminRequest -> createAdminProfile(inviteAdminRequest))
                .map(inviteAdminRequest -> new Recipient(inviteAdminRequest.getUserDetails().getName(), inviteAdminRequest.getUserDetails().getEmail()))
                .toList();
        request.getTo().addAll(recipients);

        String adminMail = AppUtilities.getAdminMailTemplate();
        request.setHtmlContent(String.format(adminMail, "admin", AppUtilities.generateVerificationLink(0L)));
        var response = mailService.sendHtmlMail(request);
        if (response!=null) return  ApiResponse.builder().message("invite requests sent").build();
        throw new BusinessLogicException("invite requests sending failed");
    }

    private Admin createAdminProfile(InviteAdminRequest inviteAdminRequest) {
        Admin admin = new Admin();
        admin.setUserDetails(new AppUser());
        admin.getUserDetails().setName(inviteAdminRequest.getName());
        admin.getUserDetails().setEmail(inviteAdminRequest.getEmail());
        adminRepository.save(admin);
        return admin;
    }

}
