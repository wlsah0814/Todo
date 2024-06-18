
// Login 요청
export const oauth2Login = (id) => {
    switch (id) {
        case 'google' :
            console.log(id);
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
    console.log(loginParam);
}

// 회원가입
export const register = (registerParam) => {
    console.log(registerParam);
}