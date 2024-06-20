import React, {useRef, useState} from "react";
import {Button, Input} from "antd";
import {defaultLogin, oauth2Login} from "../../api/LoginApi";
import {useNavigate} from "react-router-dom";

export const Login = () => {
    const emailRef = useRef(null);
    const pwRef = useRef(null);
    const navigate = useNavigate();
    const oauthLogin = [
        {
            name: 'google',
            icon: 'images/google.png',
            // href: 'https://accounts.google.com/o/oauth2/v2/auth' +
            //     '?client_id=' + process.env.REACT_APP_GOOGLE_CLIENT_ID +
            //     '&redirect_uri=' + process.env.REACT_APP_GOOGLE_REDIRECT_URI+
            //     '&response_type=' + process.env.REACT_APP_RESPONSE_TYPE +
            //     '&scope=' + process.env.REACT_APP_GOOGLE_SCOPE
            hrefL: 'http://localhost:8084/oauth2/authrization/google'
        },
        {
            name: 'kakao',
            icon: 'images/kakao.png',
            href: 'https://kauth.kakao.com/oauth/authorize' +
                '?client_id=' + process.env.REACT_APP_KAKAO_CLIENT_ID +
                '&redirect_uri=' + process.env.REACT_APP_KAKAO_REDIRECT_URI +
                '&response_type=' + process.env.REACT_APP_RESPONSE_TYPE
        },
        {
            name: 'naver',
            icon: 'images/naver.png',
            href: 'https://nid.naver.com/oauth2.0/authorize' +
                '?client_id=' + process.env.REACT_APP_NAVER_CLIENT_ID +
                '&redirect_uri=' + process.env.REACT_APP_NAVER_REDIRECT_URI +
                '&state=' + '1234' +
                '&response_type=' + process.env.REACT_APP_RESPONSE_TYPE
        }
    ];

    // 기본 로그인
    const handleLogin = () => {
        defaultLogin({
            email: emailRef.current.input.value,
            pw: pwRef.current.input.value
        })
    }

    return (
        <div className={'w-full h-full'}>
            <div className={'flex w-full h-full justify-center '}>
                <div className={'flex flex-col justify-center items-center w-[800px] h-full rounded-xl gap-10'}>
                    <div className={'flex flex-col w-[400px] gap-3'}>
                        <div className={'text-center h-[50px] text-[#757575] font-bold'}>
                            <span>: )</span>
                        </div>
                        <div className={'flex flex-col gap-2'}>
                            <Input key={'email'} type={'text'} placeholder={'이메일'} className={'h-[40px]'} ref={emailRef}/>
                            <Input key={'password'} type={'text'} placeholder={'비밀번호'} className={'h-[40px]'} ref={pwRef}/>
                        </div>
                        <div className={'w-full'}>
                            <Button
                                className={'w-full h-[50px] bg-blue-500 text-white font-bold'}
                                onClick={() => {handleLogin()}}
                            >
                                로그인
                            </Button>
                        </div>
                        <div className={'flex justify-center w-full'}>
                            <div
                                className={'text-[14px] text-[#757575] text-center cursor-pointer w-[60px] hover:text-black font-bold'}
                                onClick={() => {navigate("/register")}}
                            >
                                <span>회원가입</span>
                            </div>
                        </div>
                    </div>
                    <div className={'w-[400px] border-t-[1px] border-t-[#eee]'}>
                        <div className={'flex items-center justify-center h-[50px] text-[#757575] font-bold'}>
                            <span>간편 로그인</span>
                        </div>
                        <div className={'flex justify-center gap-3'}>
                            {oauthLogin.map(element => (
                                <a href={`${element.href}`}>
                                    <img key={`${element.name}`} alt={`${element.name}`} src={`${element.icon}`}
                                         className={'w-[40px] h-[40px] cursor-pointer'}
                                    />
                                </a>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}