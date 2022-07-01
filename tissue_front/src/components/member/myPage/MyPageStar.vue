<template>
    <v-container class="searchBox">
        <div>
            <div class="listTitle">
                <v-row justify="center" class="ml-2">
                    <v-col cols="4">
                        썸네일
                    </v-col>
                    <v-col cols="5" class="ml-5">
                        공연명
                    </v-col>
                    <v-col cols="2">
                        공연기간
                    </v-col>
                </v-row>
            </div>
            <hr class="myHr mb-8 mt-3" color="#90CAF9">
            <div class="mr-10" v-for="like in myLike" :key="like.memberNo">
                <v-row justify="center">
                    <v-btn icon  @click="dislike(like.performance.performNo)" color="red"><v-icon>mdi-heart</v-icon></v-btn>
                    <v-col cols="2" sm="2" xl="3">
                        <v-img :src="require(`@/assets/thumbNail/${like.performance.performThumbnail}`)"
                        contain class="ml-2" width="100"
                        >
                        </v-img>
                    </v-col>
                    <v-col cols="5" sm="6" xl="6" class="mt-12">
                        <router-link :to="{name:'PerformanceDetailPage', params: { performNo: like.performance.perfromNo }}"
                        style="color:black">
                        {{ like.performance.performName }}
                        </router-link>
                    </v-col>
                    <v-divider vertical class="ml-5 mt-6 mr-8" style="height:120px"/>
                    <v-col cols="3" sm="3" xl="2" class="mt-12">
                        {{ like.performance.performStart }} ~ {{ like.performance.performEnd }}
                    </v-col>
                </v-row>
                <v-divider class="mt-3 mb-3" />
            </div>
            <div class="btn-cover">
                <v-row justify="center">
                    <v-btn
                    rounded
                    :disabled="pageNum === 0"
                    @click="prevPage"
                    class="page-btn"
                    color="blue lighten-3"
                    dark
                    >
                    이전
                    </v-btn>
                    <span class="page-count"
                    >&nbsp;{{ pageNum + 1 }} / {{ pageCount }} 페이지 &nbsp;</span
                    >
                    <v-btn
                    rounded
                    :disabled="pageNum >= pageCount - 1"
                    @click="nextPage"
                    class="page-btn"
                    color="blue lighten-3"
                    dark
                    >
                    다음
                    </v-btn>
                </v-row>
          </div>
        </div>
    </v-container>
</template>

<script>
import axios from 'axios'
import { mapActions, mapState } from 'vuex'
export default {
    name:'MyPageStar',
    props: {
        memberNo: {
            type:Number
        },
        pageSize: {
            type: Number,
            required: false,
            default: 5,
        },
    },
    data() {
        return {
            pageNum: 0,
        }
    },
    computed: {
        ...mapState(['myLike']),
        pageCount() {
        let listLeng = this.myLike.length,
            listSize = this.pageSize,
            page = Math.floor(listLeng / listSize);

        if (listLeng % listSize > 0) page += 1;
        return page;
        },
        paginatedData() {
        const start = this.pageNum * this.pageSize,
            end = start + this.pageSize;
        return this.myLike.slice(start, end);
        },
    },
    mounted() {
        this.fetchMyLike(this.memberNo)
    },
    methods: {
        ...mapActions(['fetchMyLike']),
        nextPage() {
            this.pageNum += 1;
        },
        prevPage() {
            this.pageNum -= 1;
        },
        dislike(performNo) {
          let token = localStorage.getItem('token')
          axios.delete('likes/delete', { params:{performNo: performNo, token: token}})
          .then(() => {
              alert("찜이 취소되었습니다.")
              this.fetchMyLike(this.memberNo)
          })
          .catch((res) => {
              console.log(res.message)
          })
      }

    }
}
</script>

<style scoped>
.listTitle {
    font-size:15pt;
    margin-right: 2%;
    padding: 10px;  
}
.searchBox {
    zoom: 0.75;
    justify-content: center;
    align-items: center;
}
.myHr {
    margin-right:3%;
}
.btn-cover {
  margin-top: 80px;
  text-align: center;
}
</style>