package com.project.service.helper;

import com.project.entity.concretes.business.*;
import com.project.entity.image.Images;
import com.project.entity.concretes.user.User;
import com.project.entity.concretes.user.UserRole;
import com.project.entity.enums.RoleType;
import com.project.exception.BadRequestException;

import com.project.exception.ResourceNotFoundException;
import com.project.payload.messages.ErrorMessages;
import com.project.repository.user.UserRepository;
import com.project.service.user.UserRoleService;

import lombok.RequiredArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;


import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class MethodHelper {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    //!!! isUserExist
    public User isUserExist(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException(String.format(ErrorMessages.NOT_FOUND_USER_MESSAGE, userId)));
    }

    //!!! builtIn kontrolu
    public void checkBuiltIn(User user) {
        if (Boolean.TRUE.equals(user.getIsBuiltIn())) {
            throw new BadRequestException(ErrorMessages.NOT_PERMITTED_METHOD_MESSAGE);
        }
    }

    //!!! Rol kontrolu yapan metod
    public void checkRole(User user, RoleType role) {
        if (!user.getUserRole()
                .stream()
                .anyMatch(userRole -> userRole.getRole().equals(role))) {

            throw new ResourceNotFoundException(
                    String.format(ErrorMessages.NOT_FOUND_USER_WITH_ROLE_MESSAGE, user.getId(), role));
        }
    }

    //!!! username ile kontrol
    public User isUserExistByUsername(String username) {
        User user = userRepository.findByUsernameEquals(username);

        if (user.getId() == null) {
            throw new ResourceNotFoundException(ErrorMessages.NOT_FOUND_USER_MESSAGE);
        }

        return user;
    }

    public void checkRoles(User user, RoleType... roleTypes) {

        Set<RoleType> roles = new HashSet<>();
        Collections.addAll(roles, roleTypes);

        for (UserRole userRole : user.getUserRole()) {
            if (roles.contains(userRole.getRole())) return;
        }
        throw new ResourceNotFoundException(ErrorMessages.ROLE_NOT_FOUND);
    }

    public User getUserByHttpRequest(HttpServletRequest request) {
        return findByUserByEmail(getEmailByRequest(request));
    }

    public User findByUserByEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new ResourceNotFoundException("Email can not be null or empty");
        }
        return userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("User not found with email: " + email));
    }

    public String getEmailByRequest(HttpServletRequest request) {
        return (String) request.getAttribute("email");
    }

    public User getUserAndCheckRoles(HttpServletRequest request, String name) {
        User user = getUserByHttpRequest(request);
        checkRoles(user, RoleType.valueOf(name));
        return user;
    }


    public List<Images> getImagesForAdvert(MultipartFile[] files, List<Images> existingImages) {
        List<Images> imagesList = new ArrayList<>(existingImages);

        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                // Resim dosyasını işle ve Images nesnesi oluştur
                Images image = new Images();
                image.setUrl(file.getOriginalFilename()); // Örnek olarak filename kullanıyoruz, bunu değiştirebilirsiniz
                imagesList.add(image);
            }
        }

        return imagesList;
    }



    public void controlRoles(User user, RoleType... roleTypes) {

        Set<RoleType> roles = new HashSet<>();
        Collections.addAll(roles, roleTypes);
        Set<UserRole> rolesUserRole = roles.stream().map(userRoleService::getUserRole).collect(Collectors.toSet());

        for (UserRole role : user.getUserRole()) {
            if (!(rolesUserRole.contains(role))) {
                throw new BadRequestException(ErrorMessages.NOT_HAVE_AUTHORITY);
            }
        }
    }

    /*--------------------------For Report---------------------------------------*/

    private void createRow(Sheet sheet, int rowNum, CellStyle style, Object... values) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < values.length; i++) {

            Cell cell= row.createCell(i);
            cell.setCellValue(values[i].toString());
            if (style != null) {
                cell.setCellStyle(style);
            }

        }
    }

    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    public <T> ResponseEntity<byte[]> excelResponseWithList(List<T> list) {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("AdvertReport");
            int rowNum = 0;

            CellStyle headerStyle = createHeaderStyle(workbook);


            if (!list.isEmpty() && list.get(0) instanceof User) {
                createRow(sheet, rowNum++, headerStyle,"ID", "Name", "Last Name","Email","Phone");
                for (User fetchedUser : (List<User>) list) {

                    createRow(sheet, rowNum++,null, fetchedUser.getId(), fetchedUser.getFirstName(), fetchedUser.getLastName(),fetchedUser.getEmail(),fetchedUser.getPhone());
                }
            } else if (!list.isEmpty() && list.get(0) instanceof TourRequest) {
                createRow(sheet, rowNum++, headerStyle,"ID", "Name", "Last Name","Title");

                for (TourRequest tourRequest : (List<TourRequest>) list) {
                    createRow(sheet, rowNum++,null, tourRequest.getId(), tourRequest.getOwnerUser().getFirstName(),tourRequest.getOwnerUser().getLastName(), tourRequest.getAdvert().getTitle());
                }
            } else if (!list.isEmpty() && list.get(0) instanceof Advert) {
                createRow(sheet, rowNum++, headerStyle,"ID", "AdvertTitle", "Status","AdvertTypeTitle","CategoryTitle");

                for (Advert advert : (List<Advert>) list) {
                    createRow(sheet, rowNum++,null, advert.getId(), advert.getTitle(), advert.getStatus(), advert.getAdvertType().getTitle(), advert.getCategory().getTitle());
                }

            }
            else{
                throw new BadRequestException(ErrorMessages.EXCEL_COULD_NOT_BE_CREATED);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            HttpHeaders headers =returnHeader();
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            throw new BadRequestException("ERROR");
        }
    }

    public <T> ResponseEntity<byte[]> excelResponseWithPage(Page<T> list) {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("AdvertReport");
            int rowNum = 0;
            List<T> page = list.getContent();
            CellStyle headerStyle = createHeaderStyle(workbook);

            if (page.isEmpty() || !(page.get(0) instanceof Advert)){
                throw new BadRequestException(ErrorMessages.EXCEL_COULD_NOT_BE_CREATED_TYPE_IS_NOT_ADVERT);
            }
            createRow(sheet, rowNum++, headerStyle,"ID", "AdvertTitle", "Status","AdvertTypeTitle","CategoryTitle");

            for (Advert advert : (Page<Advert>) page) {
                createRow(sheet,rowNum++,null, advert.getId(),advert.getTitle(),advert.getStatus(),advert.getAdvertType().getTitle(),advert.getCategory().getTitle());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            workbook.close();

            HttpHeaders headers =returnHeader();

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);


        } catch (BadRequestException | IOException err) {
            throw new BadRequestException("ERR");
        }

    }

    private HttpHeaders returnHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("report.xlsx")
                .build());
        headers.setCacheControl(CacheControl.noCache().mustRevalidate());
        return headers;
    }



    /*-----------------------------------------------------------*/



}
