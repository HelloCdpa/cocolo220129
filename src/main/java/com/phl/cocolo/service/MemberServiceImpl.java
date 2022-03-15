package com.phl.cocolo.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.phl.cocolo.dto.MailDTO;
import com.phl.cocolo.dto.*;
import com.phl.cocolo.entity.MemberEntity;
import com.phl.cocolo.entity.PointEntity;
import com.phl.cocolo.repository.MemberMapperRepository;
import com.phl.cocolo.repository.MemberRepository;
import com.phl.cocolo.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository mr;
    private final MemberMapperRepository mmr;
    private final PointRepository pr;

    private final JavaMailSender mailSender;

    //엔티티로 저장
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IllegalStateException, IOException {
        // 프로필 사진 저장하기 파일 -> 이름
        MultipartFile m_file = memberSaveDTO.getMemberProfile();
        String m_filename = m_file.getOriginalFilename();
        m_filename = System.currentTimeMillis() + "-" + m_filename;
        // 파일 저장하기
        String savePath = "D:\\development_Phl\\source\\springboot\\CocoloProject\\src\\main\\resources\\static\\member_upload\\" + m_filename;
        if (!m_file.isEmpty()) {
            m_file.transferTo(new File(savePath));
        }
        memberSaveDTO.setMemberProfileName(m_filename);

        MemberEntity memberEntity = MemberEntity.toMemberEntitySave(memberSaveDTO);
        MemberEntity emailCheck = mr.findByMemberEmail(memberSaveDTO.getMemberEmail());

        if (emailCheck != null) {
            throw new IllegalStateException("중복된 이메일 입니다!");
        }
        return mr.save(memberEntity).getId();
    }

    @Override
    public Long saveTest(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = MemberEntity.toMemberEntitySave(memberSaveDTO);
        return mr.save(memberEntity).getId();
    }


    //이메일, 비밀번호 체크
    @Override
    public boolean findByEmail(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if (memberEntity != null) {
            if (memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //반복문으로 DTOList 에 Entity dto 로 변환한 값 담기
    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for (MemberEntity e : memberEntityList) {
            memberList.add(MemberDetailDTO.toMemberDetailDTO(e));
        }
        return memberList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        MemberEntity memberEntity = mr.findById(memberId).get();
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);

        return memberDetailDTO;
    }

    @Override
    public Long findByMemberId(String memberEmail) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
        Long memberId = memberEntity.getId();
        return memberId;
    }

    @Override
    public String emailDuplication(String memberEmail) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
        if (memberEntity == null) {
            return "ok";
        } else {
            return "no";
        }
    }

    @Override
    public String nickNameDuplication(String memberNickName) {
        MemberEntity memberEntity = mr.findByMemberNickName(memberNickName);
        if (memberEntity == null) {
            return "ok";
        } else {
            return "no";
        }

    }

    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);
    }

    @Override
    public void update(MemberUpdateDTO memberUpdateDTO) throws IllegalStateException, IOException {
        // 프로필 사진 저장하기 파일 -> 이름
        MultipartFile m_file = memberUpdateDTO.getMemberProfile();
        String m_filename = m_file.getOriginalFilename();
        m_filename = System.currentTimeMillis() + "-" + m_filename;
        // 파일 저장하기
        String savePath = "D:\\development_Phl\\source\\springboot\\MemberBoardProject\\src\\main\\resources\\member_uploadfile\\" + m_filename;
        if (!m_file.isEmpty()) {
            m_file.transferTo(new File(savePath));
        }
        memberUpdateDTO.setMemberProfileName(m_filename);

        mmr.memberUpdate(memberUpdateDTO);

    }

    //유저정보 조회
    @Override
    public String getKaKaoAccessToken(String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=198b13b4aad42557177244425bb771f9"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8091/member/kakaologin"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON 파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }

    @Override
    public String getUserInfo(String access_Token) {
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        String userEmail = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            userEmail = kakao_account.getAsJsonObject().get("email").getAsString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // catch 아래 코드 추가.
        MemberEntity result = mr.findByMemberEmail(userEmail);
        //멤버테이블에서 정보가 있나 조회

        System.out.println("S:" + result);
        if (result == null) {
            //정보가 없으면 회원가입으로 넘어가게 함
            return "no";
        } else {
            return userEmail;
            // 정보가 이미 있으면 사용자의 이메일을 리턴함.
        }


    }


    @Override
    public void pointCharge(PointSaveDTO pointSaveDTO) {
        //포인트 이력 정보 저장
        MemberEntity memberEntity = mr.findById(pointSaveDTO.getMemberId()).get();
        PointEntity pointEntity = PointEntity.toPointSaveEntity(pointSaveDTO, memberEntity);
        pr.save(pointEntity);
        System.out.println("포인트 충전");
        //회원 포인트 업데이트
        Map<String, Object> memberPointUpdate = new HashMap<>();
        memberPointUpdate.put("member_id", pointSaveDTO.getMemberId());
        memberPointUpdate.put("member_point", pointSaveDTO.getPointPoint());

        mmr.pointCharge(memberPointUpdate);

    }

    @Override
    public List<PointDetailDTO> pointFindAll(Long memberId) {
        MemberEntity memberEntity = mr.findById(memberId).get();

        List<PointEntity> pointEntityList = memberEntity.getPointEntityList();
        List<PointDetailDTO> pointList = new ArrayList<>();
        for (PointEntity p : pointEntityList) {
            pointList.add(PointDetailDTO.toPointDetailDTO(p));
        }
        return pointList;

    }

    @Override
    public void pointPayment(PointSaveDTO pointSaveDTO) {
        //포인트 이력 정보 저장
        MemberEntity memberEntity = mr.findById(pointSaveDTO.getMemberId()).get();
        PointEntity pointEntity = PointEntity.toPointSaveEntity(pointSaveDTO, memberEntity);
        pr.save(pointEntity);
        System.out.println("포인트 사용");
        //회원 포인트 업데이트
        Map<String, Object> memberPointUpdate = new HashMap<>();
        memberPointUpdate.put("member_id", pointSaveDTO.getMemberId());
        memberPointUpdate.put("member_point", pointSaveDTO.getPointPoint());

        mmr.pointCharge(memberPointUpdate);
    }

    @Override
    public MailDTO createMailAndChangePassword(String memberEmail) {
        String str = getTempPassword();
        MailDTO dto = new MailDTO();
        dto.setAddress(memberEmail);
        dto.setTitle("Cocolo 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. HOTTHINK 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                + str + " 입니다." + "로그인 후에 비밀번호를 변경을 해주세요");
        updatePassword(str,memberEmail);
        return dto;
    }

    //임시 비밀번호로 업데이트
    @Override
    public void updatePassword(String str, String userEmail){
        String memberPassword = str;
        Long memberId = mr.findByMemberEmail(userEmail).getId();
        mmr.updatePassword(memberId,memberPassword);
    }

    @Override
    public String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    @Override
    public void mailSend(MailDTO mailDTO) {
        System.out.println("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());
        message.setSubject(mailDTO.getTitle());
        message.setText(mailDTO.getMessage());
        message.setFrom("phl1021@naver.com");
        message.setReplyTo("phl1021@naver.com");
        System.out.println("message"+message);
        mailSender.send(message);
    }

    //비밀번호 변경
    @Override
    public void updatePassWord(Long memberId, String memberPassword) {
        mmr.updatePassword(memberId,memberPassword);
    }


}
