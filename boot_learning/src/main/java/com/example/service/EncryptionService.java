package com.example.service;

import com.example.service.repository.StudentRepository;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.security.MessageDigest;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by deado on 2016/11/9.
 */

@Service
public class EncryptionService {

    @Resource
    StudentRepository studentRepository;

    Character[] HEX_DIG = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private String encipherPassword(String password) throws Exception{
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytePw = password.getBytes("UTF8");
            messageDigest.update(bytePw);
            byte[] byteRes = messageDigest.digest();

            StringBuilder ret = new StringBuilder();

            for(int i=0; i<byteRes.length; i++){
                int tempContainer = byteRes[i];
                if(tempContainer < 0){
                    tempContainer+=256;
                }

                ret.append(HEX_DIG[tempContainer/16]);
                ret.append(HEX_DIG[tempContainer%16]);
            }

            return ret.toString();


        }catch(Exception e){
            throw e;
        }
    }

    private boolean checkByPython(String Id, String Password) throws Exception{
        try{
            String cmd = "python Login.py " + Id + " " + Password;
            Runtime run = Runtime.getRuntime();
            Process p = run.exec(cmd);// 启动另一个进程来执行命令
            BufferedInputStream in = new BufferedInputStream(p.getInputStream());
            BufferedReader inBr = new BufferedReader(new InputStreamReader(in));

            String res = inBr.readLine();
            while (res == null){
                res = inBr.readLine();
            }//获得命令执行后在控制台的输出信息

            if(0 == res.compareTo("False")){
                return false;
            }else{
                return true;
            }

        }catch(Exception e){
            throw e;
        }

    }

    public boolean comparePW(String studentId, String password) throws Exception{
        try{
            String md5Pw = this.studentRepository.findByMId(studentId).iterator().next().getmPassword();
            String ts = this.encipherPassword(password) + this.encipherPassword(studentId);
            if(0 == md5Pw.compareTo(
                    ts
            )){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception e){
            throw e;
        }
    }

    public String encipher(String password) throws Exception{
        try{
            return this.encipherPassword(password);
        }catch(Exception e){
            throw e;
        }
    }

    public boolean checkIdentity(String studentId, String password) throws Exception{
        try{
            return this.checkByPython(studentId, password);
        }catch(Exception e){
            throw e;
        }
    }
}
