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
                    <v-col class="mt-8 ml-10"
                    v-for="info in infos"
                    :key="info.title"
                    >
                        <v-row> 
                            <button @click="info.clickAction">{{ info.title }}</button>
                            <v-dialog 
                                v-for="d in dialog"
                                :key="d.title"
                                v-model="d.value"
                                hide-overlay
                                width="440"
                                content-class="elevation-2"
                                >
                                <grade-dialog v-if="d.title =='grade'"/>
                                <mileage-dialog v-else/>
                            </v-dialog>
                        </v-row>
                        <v-row>
                            <v-divider class="mt-3 mr-10"/>
                        </v-row>
                        <v-row v-if="info.title === '회원 등급 >'" class="infoValue mt-15">
                            {{ memberInfo.memberGrade }}
                        </v-row>
                        <v-row v-else-if="info.title === '사용 가능 쿠폰 >'" class="infoValue mt-15">
                            {{ couponsLength }}
                        </v-row>
                        <v-row v-else class="infoValue mt-15">
                            {{ memberInfo.memberMileage }}
                        </v-row>
                    </v-col>
                </v-row>
                <v-row class="main pb-8">
                    <my-page-main v-if="this.$route.name == 'MyPageView'" :memberNo="memberInfo.memberNo"/>
                    <my-page-modify v-if="this.$route.name == 'MyPageModify'" :memberInfo="memberInfo" />
                    <my-page-out v-if="this.$route.name == 'MyPageOut'" :memberNo="memberInfo.memberNo" />
                    <my-page-coupon v-if="this.$route.name == 'MyPageCoupon'" :coupons="memberInfo.coupons"/>
                    <my-page-qn-a v-if="this.$route.name == 'MyPageQnA'" :memberNo="memberInfo.memberNo"/>
                    <my-page-star v-if="this.$route.name == 'MyPageStar'" :memberNo="memberInfo.memberNo"/>
                </v-row>
            </v-main>
        </div>
</template>

<script>
import MyPageModify from './MyPageModify.vue'
import MyPageOut from './MyPageOut.vue'
import MyPageCoupon from './MyPageCoupon.vue'
import GradeDialog from './GradeDialog.vue'
import MileageDialog from './MileageDialog.vue'
import MyPageQnA from './MyPageQnA.vue'
import MyPageStar from './MyPageStar.vue'
import MyPageMain from './MyPageMain.vue'
export default {
  components: { MyPageModify, MyPageOut, MyPageCoupon, GradeDialog, MileageDialog, MyPageQnA, MyPageStar, MyPageMain },
    name: 'MyPageNavi',
    props:{
        memberInfo: {
            type:Object,
            required: true
        },
        couponsLength: {
            type: Number
        }
    },
    data () {
        return {
            dialog: [ 
                {title: 'grade', value: false},
                {title: 'mileage', value: false}
            ],
            items: [
                { title: 'My 정보수정', icon: 'mdi-account', route:'/myPage/modify' },
                { title: 'My 찜목록', icon: 'mdi-star', route:'/myPage/star' },
                { title: 'My 예매 내역', icon: 'mdi-book' },
                { title: 'My QnA', icon: 'mdi-chat-question', route:'/myPage/qna'},
                { title: 'My 후기', icon: 'mdi-pencil' },
                { title: '회원 탈퇴', icon: 'mdi-emoticon-confused', route:'/myPage/signOut'}
            ],
            infos: [
                { title: '회원 등급 >', 
                    clickAction:() =>{
                        this.showPage(1)
                } },
                { title: '사용 가능 쿠폰 >', 
                    clickAction:() => {
                        this.showPage(2)}
                },
                { title: '마일리지 >', 
                    clickAction:() => {
                        this.showPage(3)
                } } 
            ],
        }
    },
    methods: {
        showPage (title) {
            if (title == 1){
                this.dialog[0].value = true
            } else if (title == 2) {
                this.$router.push('/myPage/coupon')
            } else {
                this.dialog[1].value = true
            }
        },
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
    position: relative;
    margin-bottom:5%;
    height: 205px;
    width:101%;
    background: #d6edff;
}
.status{
    font-size:23pt;
}
.main {
    position: inherit;
    left: 65%;
    transform: translate(-60%);
}
.infoValue{
    font-size:25pt;
}

</style>