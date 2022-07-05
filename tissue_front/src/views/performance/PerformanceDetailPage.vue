<template>
  <div>
    <performance-detail
      v-if="performance || performanceEvent"
      :performance="performance"
      :couponList="couponList"
      :likeList="likeList"
      :likeMember="likeMember"
      :performanceEvent="performanceEvent"
      :reviewList="reviewList"
      @update:likeList="likeList = $event"
      @update:likeMember="likeMember = $event"
    />
  </div>
</template>

<script>
import PerformanceDetail from "@/components/performance/PerformanceDetail.vue";
import { mapActions, mapMutations, mapState } from "vuex";
import axios from 'axios';

export default {
  name: "PerformanceDetailPage",
  components: {
    PerformanceDetail,
  },
  props: {
    performNo: {
      type: String,
      required: true,
    },
  },
  data() {
      return {
          likeMember: false
      }
  },
  computed: {
      likeList: {
          ...mapState({get:'likeList'}),
          ...mapMutations({set:'FETCH_PERFORMANCE_LIKE'})
      },
    ...mapState([
      "performance",
      "couponList",
      "performanceEvent",
      "reviewList",
    ]),
  },
  mounted() {
    this.fetchPerformance(this.performNo);
    this.fetchCouponList();
    this.fetchPerformanceLike(this.performNo);
    this.checkMember()
    this.fetchPerformanceEvent(this.performNo);
    this.fetchPerformanceReviewList(this.performNo);
  },
  methods: {
    ...mapActions([
      "fetchPerformance", 
      "fetchCouponList", 
      "fetchPerformanceLike", 
      "fetchPerformanceEvent",
      "fetchPerformanceReviewList"]),
     checkMember () {
          let token = localStorage.getItem('token')
          if(token != null){
            axios.get('likes/member' , { params:{token:token} })
            .then((res) => {
                for (let i = 0; i <this.likeList.length; i++){
                  
                    if(this.likeList[i].member.memberNo === res.data) {
                        return this.likeMember = true
                    }
                }
                return this.likeMember = false
            })
          }
     }
  }
}
</script>
