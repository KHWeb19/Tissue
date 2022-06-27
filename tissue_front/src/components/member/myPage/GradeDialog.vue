<template>
    <v-card>
        <v-card-title class="grey lighten-2">
            🎈 회원 등급 안내
        </v-card-title>
        <v-divider/>
        <v-card-text class="mt-5">
            일반 : 처음 Tissue에 가입하였거나 한달 3회 미만의 예매 서비스를 이용하신 고객님 <p/>
            Vip : 한달 3회 이상 Tissue의 예매 서비스를 이용하신 고객님<br/>
            <v-icon small>mdi-check</v-icon> Vip 등급은 예매 시 결제금액의 10%의 할인 혜택이 주어집니다.
        </v-card-text>
    </v-card>
</template>
<script>
export default {
    name:'GradeDialog',

}
</script>

<style scoped>
.weight{
    font-weight: bold;
}
</style>