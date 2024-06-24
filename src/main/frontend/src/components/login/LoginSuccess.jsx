import React from "react";
import {useLocation, useNavigate} from "react-router-dom";

export const LoginSuccess = () => {
    const navigate = useNavigate();
    return (
        <div className={'flex flex-col w-full h-full justify-center items-center gap-4'}>
            <div>
                <span className={'text-[30px] font-bold'}>회원가입 ㅊㅊ</span>
            </div>
            <div>
                <span
                    className={'text-[30px] underline text-blue-500 hover:text-blue-700 cursor-pointer'}
                    onClick={() => navigate("/")}
                >
                    로그인 ㄱㄱ
                </span>
            </div>
        </div>
    )
}