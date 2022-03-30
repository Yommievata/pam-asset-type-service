package ikea.imc.pam.asset.type.service.service;

import ikea.imc.pam.asset.type.service.repository.model.UserInformation;

public interface UserService {

    UserInformation getUserInformation(String userId);
}
