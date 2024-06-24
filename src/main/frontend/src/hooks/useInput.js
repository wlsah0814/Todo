import {useState} from "react";

/**
 * 로그인 / 회원가입 hook
 * @returns {[{password: string, email: string},handleSetInputParam]}
 */
export const useInput = () => {
    const [inputParam, setInputParam] = useState({email: '', password: ''});

    const handleSetInputParam = (evt) => {
        setInputParam(prevState => {
            return {...prevState, [evt.target.name]: evt.target.value};
        });
    }

    return [inputParam, handleSetInputParam]
}