import React, {useRef} from "react";
import {Button, Input} from "antd";
import {register} from "../../api/LoginApi";

export const Register = () => {
    const emailRef = useRef(null);
    const pwRef = useRef(null);

    const userRegister = () => {
        register({
            email: emailRef.current.input.value,
            pw: pwRef.current.input.value
        });
    }

    return (
        <div className={' w-full h-full'}>
            <div className={'flex justify-center items-center w-full h-full'}>
                <div className={'flex flex-col w-[400px] gap-3'}>
                    <div className={'text-center h-[50px] text-[#757575] font-bold'}>
                        <span>: (</span>
                    </div>
                    <div className={'flex flex-col gap-2'}>
                        <Input key={'email'} type={'text'} placeholder={'이메일'} className={'h-[40px]'} ref={emailRef}/>
                        <Input key={'password'} type={'text'} placeholder={'비밀번호'} className={'h-[40px]'} ref={pwRef}/>
                    </div>
                    <div className={'w-full'}>
                        <Button
                            className={'w-full h-[50px] bg-blue-500 text-white font-bold'}
                            onClick={() => {userRegister()}}
                        >
                            회원가입
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    )
}