import React, {useState} from "react";
import {Button, Input} from "antd";
import {register} from "../../api/LoginApi";
import {useNavigate} from "react-router-dom";

export const Register = () => {
    const navigate = useNavigate();
    const [param, setParam] = useState({email: '', password: ''});

    const handleSetParam = (evt) => {
        setParam(prevState => {
            return {...prevState, [evt.target.name] : evt.target.value}
        });
    }


    const handleSubmit = async () => {
        const result = await register(param, '/');
        if(result === 200) {
            navigate("/");
        }
    }

    return (
        <div className={' w-full h-full'}>
            <div className={'flex justify-center items-center w-full h-full'}>
                <div className={'flex flex-col w-[400px] gap-3'}>
                    <div className={'text-center h-[50px] text-[#757575] font-bold'}>
                        <span>: (</span>
                    </div>
                    <div className={'flex flex-col gap-2'}>
                        <Input key={'email'} name={'email'} type={'text'} placeholder={'이메일'} className={'h-[40px]'} onChange={handleSetParam}/>
                        <Input key={'password'} name={'password'} type={'password'} placeholder={'비밀번호'} className={'h-[40px]'} onChange={handleSetParam}/>
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