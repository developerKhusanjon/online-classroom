package io.khusanjon.onlineclassroom.service.impl;

import io.khusanjon.onlineclassroom.model.domain.Staff;
import io.khusanjon.onlineclassroom.model.domain.StaffSession;
import io.khusanjon.onlineclassroom.model.dto.StaffSessionDto;
import io.khusanjon.onlineclassroom.model.mapper.StaffSessionMapper;
import io.khusanjon.onlineclassroom.repository.StaffRepository;
import io.khusanjon.onlineclassroom.repository.StaffSessionRepository;
import io.khusanjon.onlineclassroom.service.StaffSessionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Mamadaliyev Nodirbek
 * @created 18/06/2021 - 16:07
 */
@Service
public class StaffSessionServiceImpl implements StaffSessionService {

    private final StaffSessionMapper staffSessionMapper;
    private final StaffSessionRepository staffSessionRepository;
    private final StaffRepository staffRepository;

    public StaffSessionServiceImpl(StaffSessionMapper staffSessionMapper,
                                   StaffSessionRepository staffSessionRepository,
                                   StaffRepository staffRepository) {
        this.staffSessionMapper = staffSessionMapper;
        this.staffSessionRepository = staffSessionRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    public Page<StaffSessionDto> findAll(Pageable pageable, Staff staff_id) {
        return staffSessionRepository
                .findAllByStaffId(staff_id.getId(), pageable).map(staffSessionMapper::toDto);
    }

    @Override
    public void save(HttpServletRequest request, Staff staff, String token) {
        StaffSession staffSession = new StaffSession();
        staffSession.setStaff(staff);
        staffSession.setToken(token);
        staffSession.setDeviceIp(getClientIpAddress(request));
        staffSession.setDeviceModel(getDeviceModel(request));
        staffSession.setDeviceOsVersion(getDeviceOsVersion(request));
        staffSessionRepository.save(staffSession);
    }

    private String getDeviceModel(HttpServletRequest request) {
        return request.getHeader("device-model");
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String LOCALHOST_IPV4 = "127.0.0.1";
        String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }

        return ipAddress;
    }

    private String getDeviceOsVersion(HttpServletRequest request) {
        return request.getHeader("device-os");
    }
}
