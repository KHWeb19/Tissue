<template>
        <v-card class="flex">
            <v-navigation-drawer permanent>
                <v-list-item>
                    <v-list-item-content>
                        <v-list-item-title class="text-h3 mt-8 ml-5" >
                            <router-link to="/myPage" style="color:black"> My Page </router-link>
                        </v-list-item-title>
                        <v-list-item-subtitle class="ml-5 mt-5 mb-10">
                            유아림 님
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
            <v-main class="ml-3">
                <v-row class="statusBox">
                    <v-col class="mt-5 ml-5"
                    v-for=" status in status"
                    :key="status.title">
                        <v-row>
                            <v-col>
                                {{status.title}} >
                            </v-col>
                            <v-col>
                            </v-col>
                        </v-row>   
                        <v-row class="status mt-12">
                            <v-col>
                                {{status.status}}
                            </v-col>
                        </v-row>
                        <v-divider class="mt-2"/>
                    </v-col>
                </v-row>
                <v-row justify="center" class="main">
                    <my-page-modify v-if="this.$route.name=='MyPageModify'"/>
                    <div v-else>메인</div>
                </v-row>
            </v-main>
        </v-card>
</template>

<script>
import MyPageModify from '@/components/member/myPage/MyPageModify.vue'
export default {
  components: { MyPageModify },
    name:'MyPageView',
    data () {
        return {
            items: [
                { title: 'My 정보수정', icon: 'mdi-view-dashboard', route:'/myPage/modify' },
                { title: 'My 찜목록', icon: 'mdi-image' },
                { title: 'My 예매 내역', icon: 'mdi-help-box' },
                { title: 'My QnA', icon: 'mdi-view-dashboard' },
                { title: 'My 후기', icon: 'mdi-image' },
            ],
            status: [
                {title: '회원 등급', status: '회원등급'},
                {title: '사용 가능 쿠폰', status: '사용가능쿠폰'},
                {title: '마일리지', status: '마일리지'}
            ]
        }
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
    height: 205px;
    background: rgb(220, 240, 248);
}
.status{
    font-size:23pt;
    float: left;
}
.main {
    position: relative;
    left:50%;
    top: 25%;
    transform: translate(-50%, -25%);
    font-size:40pt;
}
</style>