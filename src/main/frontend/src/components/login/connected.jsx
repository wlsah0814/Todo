import React from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {Button} from "antd";
import {accountConnected} from "../../api/LoginApi";

export const Connected = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const useQuery = () => {
        return new URLSearchParams(location.search);
    }
    const query = useQuery();
    const connectedData = {
        "email" : query.get("email"),
        "provider" : query.get("provider"),
        "providerId" : query.get("providerId"),
    };

    // 계정 연동 요청
    const isConnected = async () => {
        const result = await accountConnected(connectedData);
        result === 200 ? successConnected() : failConnected();
    }

    const successConnected = () => {
        alert("연동 완료!");
        navigate("/home");
    }

    const failConnected = () => {
        alert("연동 실패! 다시 로그인 해주세요.");
        navigate("/")
    }

    return (
        <div className={'w-full h-full'}>
            <div className={'flex flex-col w-full h-full justify-center items-center gap-2'}>
                <div>
                    <span className={'text-[20px] font-bold'}>⚡️ {connectedData.email} 님의 계정을 연동하시겠습니까? ⚡️</span>
                </div>
                <div className={'flex flex-col gap-2 cursor-pointer'}>
                    <Button className={'w-[300px] h-[50px] bg-blue-500 text-white font-bold'} onClick={isConnected}>
                        연동하기
                    </Button>
                    <Button className={'w-[300px] h-[50px] bg-gray-500 text-white font-bold'} onClick={() => {navigate("/")}}>
                        취소
                    </Button>
                </div>
            </div>
        </div>
    )
}