<template>
    <div class="background">
        <v-container>
            <div class="mt-7 topBtn">
                    <v-btn depressed color="blue lighten-3" dark to="/join">회원가입</v-btn>
            </div>
            <v-row justify="center" class="mt-3 mb-3">
                <v-col cols="9">
                    <div class="logo">
                        <router-link to="/"><div style="color:skyblue; float:left;">T</div><div style="color:pink; float:left;">issue</div></router-link>
                    </div>
                </v-col>
            </v-row>
            <div class="box pl-5 pr-5 pb-15 mb-5">
                <v-row justify="center" class="mt-12">
                    <v-col cols="5" xl="4" class="mr-13">
                        <member-login/>
                    </v-col>
                    <v-col cols="5" xl="4">
                        <v-img src="@/assets/testtest.png"></v-img>
                        <v-img src="@/assets/testtest2.jpg"></v-img>
                    </v-col>
                </v-row>
            </div>
        </v-container>
    </div>
</template>

<script>
import MemberLogin from '@/components/member/MemberLogin.vue'

export default {
  components: { MemberLogin },

    name:'MemberLoginPage',

}
</script>

<style scoped>
.background {
    background-color: rgb(241, 241, 241);
}
.logo {
    font-size: 50pt;
    margin-top:5%;
    font-family: 'Pacifico', cursive;
}
.box {
    background-color: rgb(255, 255, 255);
    border: 1px solid rgb(197, 197, 197);
    width:82%;
    margin: auto;
}
.topBtn{
    position: absolute;
    left:72%;
}

@media (min-width: 1800px) {
    .topBtn{
    position: absolute;
    left:80%;
    zoom:1.2;
    }
    .background {
    background-color: rgb(241, 241, 241);
    }
}

</style>