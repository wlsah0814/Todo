
// Login 요청
import axios from "axios";

// 기본 로그인
export const defaultLogin = (loginParam) => {
    return console.log(loginParam);
}

// 회원가입
export const register = async (registerParam) => {
    return await axios.post("/user/register", registerParam)
        .then((response) => {
            return response.status;
        })
        .catch((error) => {
            return error.response.status;
        })
}