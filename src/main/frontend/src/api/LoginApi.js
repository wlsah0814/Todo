
// Login 요청
import axios from "axios";

export const oauth2Login = (id) => {
    switch (id) {
        case 'google' :
            axios.get('/oauth2/authorization/google')
                .then(response => {
                    console.log(response);
                })
                .catch((error) => {
                    console.log(error);
                })
            break;
        case 'kakao' :
            console.log(id);
            break;
        case 'naver' :
            console.log(id);
            break;
    }
}

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
            return error.data.status;
        })
}