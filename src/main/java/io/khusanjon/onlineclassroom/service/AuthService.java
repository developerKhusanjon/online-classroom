package io.khusanjon.onlineclassroom.service;


import io.khusanjon.onlineclassroom.controller.vm.LoginVM;
import io.khusanjon.onlineclassroom.model.dto.JWTTokenDto;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021-15:07
 */
public interface AuthService {

    JWTTokenDto loginUser(HttpServletRequest request, LoginVM loginVM);

}
