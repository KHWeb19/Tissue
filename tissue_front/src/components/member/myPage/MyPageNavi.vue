<template>
        <div class="flex" >
            <v-navigation-drawer permanent  height="">
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title class="text-h3 mt-8 ml-5" >
                            <router-link to="/myPage" style="color:black"> My Page </router-link>
                        </v-list-item-title>
                        <v-list-item-subtitle class="ml-5 mt-5 mb-10">
                            {{ memberInfo.memberName }} 님
                        </v-list-item-subtitle> 
                    </v-list-item-content>
                </v-list-item>
                <v-divider></v-divider>
                <v-list
                    dense
                    nav
                    class="ml-5 mt-5 mb-10"
                >
                    <v-list-item
                    v-for="item in items"
                    :key="item.title"
                    :to="item.route"
                    link
                    class="mt-5" 
                    >
                    <v-list-item-icon>
                        <v-icon>{{ item.icon }}</v-icon>
                    </v-list-item-icon>
                    <v-list-item-content>
                        <v-list-item-title>{{ item.title }}</v-list-item-title>
                    </v-list-item-content>
                    </v-list-item>
                </v-list>
            </v-navigation-drawer>
            <v-main class="ml-3" >
                <v-row class="statusBox">
                    <v-col class="mt-8 ml-10">
                        <v-row>
                            <v-col >
                                <v-row class="mb-5">
                                회원등급 >
                                </v-row>
                                <v-row class="status mt-16">
                                    {{memberInfo.memberGrade}}
                                </v-row>
                            </v-col>
                            <v-divider  vertical class="mr-8"/>
                            <v-col>
                                <v-row class="mb-5">
                                사용 가능 쿠폰 >
                                </v-row>
                                <v-row class="status mt-16">
                                    사용가능 쿠폰
                                </v-row>
                            </v-col>
                            <v-divider  vertical class="mr-8"/>
                            <v-col>
                                <v-row class="mb-5">
                                마일리지 >
                                </v-row>
                                <v-row class="status mt-16">
                                    {{memberInfo.memberMileage}}
                                </v-row>
                            </v-col>
                        </v-row>   
                    </v-col>
                </v-row>
                <v-row class="main mb-15 pb-15">
                    <my-page-modify v-if="this.$route.name == 'MyPageModify'" :memberInfo="memberInfo" />
                    <my-page-out v-if="this.$route.name == 'MyPageOut'" :memberNo="memberInfo.memberNo" />
                </v-row>
            </v-main>
        </div>

</template>

<script>
import MyPageModify from '@/components/member/myPage/MyPageModify.vue'
import MyPageOut from './MyPageOut.vue'
export default {
  components: { MyPageModify, MyPageOut },
    name:'MyPageNavi',
    props:{
        memberInfo: {
            type:Object,
            required: true
        }
    },
    data () {
        return {
            items: [
                { title: 'My 정보수정', icon: 'mdi-account', route:'/myPage/modify' },
                { title: 'My 찜목록', icon: 'mdi-star' },
                { title: 'My 예매 내역', icon: 'mdi-book' },
                { title: 'My QnA', icon: 'mdi-chat-question' },
                { title: 'My 후기', icon: 'mdi-pencil' },
                { title: '회원 탈퇴', icon: 'mdi-emoticon-confused', route:'/myPage/signOut'}
            ],
        }
    },
    methods: {
    }
}
</script>

<style scoped>
.flex {
    display: flex;
    height: 100%;
    width: 100%;
}
.statusBox {
    position: inherit;
    height: 205px;
    background: rgb(220, 240, 248);
}
.status{
    font-size:23pt;
}
.main {
    position: relative;
    left: 65%;
    top: 30%;
    transform: translate(-60%, -30%);
}

</style>