<template>
    <div class="background">
        <v-container>
            <v-row justify="center" class="logo"><div style="color:skyblue">T</div><div style="color:pink">issue</div></v-row>
            <member-join-term class="mb-5"/>
        </v-container>
    </div>
</template>

<script>
import MemberJoinTerm from '@/components/member/MemberJoinTerm.vue';

export default {
  components: {MemberJoinTerm},
  name: 'MemberJoinPage',
}
</script>

<style scoped>
.background{
    background-color: rgb(241, 241, 241);
}
.logo {
    font-size: 50pt;
    margin-top:5%;
    font-family: 'Pacifico', cursive;
}
</style>