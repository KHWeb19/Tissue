<template>
    <v-container>
        <span class="ml-3" style="font-size:20pt;"> My 후기 </span>
        <p/>
        <hr color="#90CAF9" width="95%">
        <v-data-table
        class="mr-10 mb-8"
        :headers="headers"
        :items="myReview"
        :page.sync="page"
        :items-per-page="itemsPerPage"
        @page-count="pageCount = $event"
         hide-default-footer
        >
        <template v-slot:[`item.performThumbnail`] ="{item}">
                <img class="imgwidth"
                :src="require(`@/assets/thumbNail/${item.performThumbnail}`)"
               >
        </template>
        <template v-slot:[`item.reviewRating`] ="{item}">
            <v-rating
                :value="item.reviewRating"
                background-color="orange lighten-3"
                color="orange"
                x-small
                hover
                dense
                readonly
            ></v-rating>
        </template>
        <template v-slot:[`item.performName`] ="{item}">
            <span class="pName"> {{item.performName}} </span>
        </template>
        <template v-slot:[`item.reviewRegDate`] ="{item}">
            <span class="pName"> {{item.reviewRegDate}} </span>
        </template>
        <template v-slot:[`item.delete`]="{item}">
                <v-icon small @click="show(item)">mdi-pencil</v-icon>
                <v-icon smal @click="deleteReview(item.reviewNo)"> delete </v-icon>
                <v-dialog v-model="reviewDialog" width="750" hide-overlay>
                    <v-card height="100%" style="border: 1px solid black">
                    <div
                        style="
                        display: flex;
                        justify-content: space-between;
                        border-bottom: 2px solid black;
                        margin-left: 30px;
                        margin-right: 30px;
                        "
                    >
                        <div style="font-size: 25px; padding: 25px">
                        {{ performName }}
                        </div>
                        <div>
                        <v-btn icon @click="reviewDialog = false"> X </v-btn>
                        </div>
                    </div>
                    <div
                        style="
                        display: flex;
                        border-bottom: 1px solid black;
                        margin-left: 30px;
                        margin-right: 30px;
                        "
                    >
                        <div class="reviewSub">별점</div>
                        <div>
                        <v-rating
                            v-model="reviewRating"
                            background-color="orange lighten-3"
                            color="orange"
                            large
                            style="padding-top: 12px"
                        ></v-rating>
                        </div>
                    </div>
                    <div
                        style="
                        display: flex;
                        border-bottom: 1px solid lightgrey;
                        margin-left: 30px;
                        margin-right: 30px;
                        "
                    >
                    <div class="reviewSub">관람 후기</div>
                    <div>
                    <v-textarea
                        v-model="reviewContent"
                        label="리뷰"
                        placeholder="내용을 작성해주세요."
                        clearable
                        auto-grow
                        outlined
                        color="pink lighten-3"
                        style="padding-top: 12px; width: 550px"
                    />
                    </div>
                </div>
                <div>
                    <div
                    style="
                        margin-left: 30px;
                        margin-right: 30px;
                        color: #90caf9;
                        font-size: 16px;
                        margin-top: 10px;
                        margin-bottom: 10px;
                    "
                    >
                    <v-icon small color="blue lighten-3"
                        >mdi-exclamation</v-icon
                    >
                    관람후기 작성 시 유의사항
                    </div>
                    <div
                    style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                        margin-bottom: 10px;
                    "
                    >
                    Tissue 게시판 운영 규정에 위반된다고 판단되는 글은 사전고지
                    없이 삭제될 수 있습니다.
                    </div>
                    <div
                    style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                    "
                    >
                    <v-icon>mdi-check</v-icon>
                    티켓 매매 및 양도, 교환의 글
                    </div>
                    <div
                    style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                    "
                    >
                    <v-icon>mdi-check</v-icon>
                    전화번호, 이메일, 주소 등 회원 및 타인의 개인정보 유출이
                    우려되는 경우
                    </div>
                    <div
                    style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                    "
                    >
                    <v-icon>mdi-check</v-icon>
                    욕설, 비방, 도배성 글을 작성한 경우
                    </div>
                    <div
                    style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                    "
                    >
                    <v-icon>mdi-check</v-icon>
                    명예훼손, 저작권, 초상권의 권리 침해 및 음란한 사진을 게재한
                    경우
                    </div>
                    <div
                    style="
                        margin-left: 30px;
                        margin-right: 30px;
                        font-size: 13px;
                        margin-top: 10px;
                        margin-bottom: 10px;
                        padding-bottom: 10px;
                        border-bottom: 1px solid black;
                    "
                    >
                    작성된 게시물의 저작권은 작성자에게 있으며, 게시물로 인해
                    발생하는 문제는 작성자 본인에게 책임이 있습니다. 작성 시
                    유의해주시기 바랍니다.
                    </div>
                </div>
                <div align="center" style="margin: 25px">
                    <v-btn
                    width="150"
                    height="50"
                    @click="modifyReview(performName)"
                    color="blue lighten-3"
                    class="white--text mr-3"
                    style="font-size: 15px"
                    >수정</v-btn
                    >
                    <v-btn
                    width="150"
                    height="50"
                    @click="reviewDialog = false"
                    class="blue--text text--lighten-3 ml-3"
                    style="border: 1px solid #90caf9; background-color: white"
                    >취소</v-btn
                    >
                </div>
                </v-card>
            </v-dialog>
        </template>
        </v-data-table>
        <v-pagination
            class="mt-10"
            v-model="page"
            :length="pageCount"
            total-visible="5"
            color="pink lighten-3"
            circle>
        </v-pagination>
    </v-container>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
export default {
    name:'MyPageReview',
    props: {
        memberNo :{
            type:Number
        }
    },
    data() {
        return {
            page: 1,
            pageCount: 0,
            itemsPerPage: 7,
            reviewDialog:false,
            performName:'',
            reviewContent:'',
            reviewRating: '',
            reviewNo:null,
            headers: [
                {text:'썸네일', value:'performThumbnail'},
                {text:'공연명', value:'performName', width:'170'},
                {text:'후기', value:'reviewContent', width:'250'},
                {text:'평점', value:'reviewRating',width:'100'},
                {text:'등록날짜', value:'reviewRegDate',width:'140'},
                {text:'Action', value:'delete', width:'90'}

            ]
        }
    },
    computed: {
        ...mapState(['myReview'])
    },
    mounted() {
        this.fetchMyReview(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyReview']),
        show(item) {
            this.reviewNo = item.reviewNo
            this.performName = item.performName
            this.reviewContent = item.reviewContent
            this.reviewRating = item.reviewRating
            this.reviewWriter= item.reviewWriter
            this.reviewDialog = true;
        },
        modifyReview(performName) {
            const{reviewNo, reviewWriter, reviewContent, reviewRating} = this
            axios
            .put(`review/modify/${performName}`, {
                reviewNo,
                reviewContent,
                reviewRating,
                reviewWriter,
          })
          .then(() => {
            alert("후기가 수정되셨습니다.");
            this.reviewDialog = false
            history.go()
          })
          .catch(() => {
            alert("실패");
          });
      },
      deleteReview(reviewNo) {
          axios.delete(`review/delete/${reviewNo}`)
          .then(() => {
              alert("후기가 삭제되셨습니다.")
              history.go()
          })
      }
    }
}
</script>

<style scoped>
.imgwidth{
    width: 60px;
    margin-top:7px;
}
.pName{
    font-family: 'Nanum Gothic', sans-serif !important;
    font-size: 9.5pt;
}
.reviewBox {
  padding: 50px;
}
.reviewTitle {
  border-bottom: 2px solid black;
  font-size: 28px;
  padding-bottom: 7px;
  margin-right: 20px;
}
.reviewDes {
  margin-top: 30px;
  margin-bottom: 30px;
  font-size: 15px;
}
.reviewSub {
  width: 120px;
  font-size: 16px;
  padding: 25px;
}
</style>