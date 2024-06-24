import React, {useState} from "react";
import {Button, Input} from "antd";
import {register} from "../../api/LoginApi";
import {useNavigate} from "react-router-dom";
import {useInput} from "../../hooks/useInput";

export const Register = () => {
    const navigate = useNavigate();
    const [inputParam, handleSetInputParam] = useInput();

    const handleSubmit = async () => {
        const result = await register(inputParam)
        result === 200 ? navigate("/login/success") : alert('실패');
    }

    return (
        <div className={'w-full h-full'}>
            <div className={'flex justify-center items-center w-full h-full'}>
                <div className={'flex flex-col w-[400px] gap-3'}>
                    <div className={'text-center h-[50px] text-[#757575] font-bold'}>
                        <span>: (</span>
                    </div>
                    <div className={'flex flex-col gap-2'}>
                        <Input key={'email'} name={'email'} type={'text'} placeholder={'이메일'} className={'h-[40px]'} onChange={handleSetInputParam}/>
                        <Input key={'password'} name={'password'} type={'password'} placeholder={'비밀번호'} className={'h-[40px]'} onChange={handleSetInputParam}/>
                    </div>
                    <div className={'w-full'}>
                        <Button
                            className={'w-full h-[50px] bg-blue-500 text-white font-bold'}
                            onClick={handleSubmit}
                        >
                            회원가입
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    )
}