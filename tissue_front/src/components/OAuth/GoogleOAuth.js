const ClientId = '749462997498-0a9inj9a7sd1gcvhnqaucs4ri8liotth.apps.googleusercontent.com'
const RedirectUri = 'http://localhost:8080/googleLogin'

export const GoogleAuthUri = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${ClientId}&redirect_uri=${RedirectUri}&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email`;