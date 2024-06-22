import React, {useRef, useState} from "react";
import {Button, Input} from "antd";
import {defaultLogin, oauth2Login} from "../../api/LoginApi";
import {useNavigate} from "react-router-dom";

const OAUTH_LOGIN = Object.freeze([
    {
        name: 'google',
        icon: 'images/google.png',
        href: 'http://localhost:8084/oauth2/authorization/google'
    },
    {
        name: 'kakao',
        icon: 'images/kakao.png',
        href: 'http://localhost:8084/oauth2/authorization/kakao'
    },
    {
        name: 'naver',
        icon: 'images/naver.png',
        href: 'http://localhost:8084/oauth2/authorization/naver'
    }
])

export const Login = () => {
    const navigate = useNavigate();
    const [loginParam, setLoginParam] = useState({
        email: '',
        password: '',
    })

    // 로그인 파라미터 set
    const handleSetLoginParam = (evt) => {
        setLoginParam(prevState => {
            return {...prevState, [evt.target.name]: evt.target.value};
        })
    }

    // 기본 로그인
    const handleLogin = () => {
       const result = defaultLogin(loginParam)
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
                            <Input key={'email'} name={'email'} type={'text'} placeholder={'이메일'} className={'h-[40px]'} onChange={handleSetLoginParam} />
                            <Input key={'password'} name={'password'} type={'text'} placeholder={'비밀번호'} className={'h-[40px]'} onChange={handleSetLoginParam} />
                        </div>
                        <div className={'w-full'}>
                            <Button
                                className={'w-full h-[50px] bg-blue-500 text-white font-bold'}
                                onClick={handleLogin}
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
                            {OAUTH_LOGIN.map(element => (
                                <a href={element.href}>
                                    <img key={element.name} alt={element.name} src={element.icon}
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