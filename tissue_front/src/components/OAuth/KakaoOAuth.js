const ClientId = '3395df40560e8a559777cb689768c85b'
const RedirectUri = 'http://localhost:8080/kakaoLogin'

export const KakaoAuthUri = `https://kauth.kakao.com/oauth/authorize?client_id=${ClientId}&redirect_uri=${RedirectUri}&response_type=code`
