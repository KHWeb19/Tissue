<template>
    <div class="backGround">
        <v-container class="center">
            <v-row justify="center">
                <div class="logo">
                    <div style="color:skyblue; float:left;">T</div><div style="color:pink; float:left;">issue</div>
                </div>
            </v-row>
            <v-row justify="center">
                <div style="color:grey; margin-left:30px; font-size:20pt">로그인 중입니다.</div>
                &nbsp;
                <v-progress-circular
                    indeterminate
                    color="blue lighten-3"
                ></v-progress-circular>
            </v-row>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    name:'GoogleOAuth',
    created () {

        let code = new URL(window.location.href).searchParams.get("code")
        this.googleLogin (code)

    },
    methods: {
        googleLogin (payload) {
            const code = payload
            console.log(code)
            axios('google', {
                method: "get",
                baseURL: 'http://localhost:5000/',
                params: {
                    code: code
                }
            })
            .then((res) => {
                console.log(res)
                localStorage.setItem("token", res.data.access_token)
                this.$router.push("/")
                console.log(res.data.access_token)
            }). catch((err) => {
                console.log(err)
            })
        }
    }
}
</script>

<style scoped>
.backGround {
    background-color: rgb(241, 241, 241);
    height: 100vh;
    background-size: cover ;
}
.logo {
    font-size: 80pt;
    font-family: 'Pacifico', cursive;
}
.center {
    position: absolute;
    left:50%;
    top: 48%;
    transform: translate(-50%, -50%);
}
</style>