const ClientId = 'a9dc62fd6bf5972a40961d2f9cd317e9'
const RedirectUri = 'http://localhost:8080/kakaoLogin'

export const KakaoAuthUri = `https://kauth.kakao.com/oauth/authorize?client_id=${ClientId}&redirect_uri=${RedirectUri}&response_type=code`
