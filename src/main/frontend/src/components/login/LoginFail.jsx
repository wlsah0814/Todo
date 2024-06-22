import React from "react";
import {useLocation, useNavigate} from "react-router-dom";

export const LoginFail = () => {
    const navigate = useNavigate();
    return (
        <div className={'flex flex-col w-full h-full justify-center items-center gap-4'}>
            <div>
                <span className={'text-[40px] font-bold'}>로그인 실패</span>
            </div>
            <div>
                <span
                    className={'text-[40px] underline text-blue-500 hover:text-blue-700 cursor-pointer'}
                    onClick={() => navigate("/")}
                >
                    돌아가기
                </span>
            </div>
        </div>
    )
}