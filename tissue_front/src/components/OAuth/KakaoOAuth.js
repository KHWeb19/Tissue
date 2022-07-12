const ClientId = '1ca3145ada53150bac1d6836a34cb5ec'
const RedirectUri = 'http://localhost:8080/kakaoLogin'

export const KakaoAuthUri = `https://kauth.kakao.com/oauth/authorize?client_id=${ClientId}&redirect_uri=${RedirectUri}&response_type=code`
