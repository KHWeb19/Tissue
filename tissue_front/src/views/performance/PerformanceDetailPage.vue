<template>
  <div>
    <performance-detail
      v-if="performance"
      :performance="performance"
      :couponList="couponList"
      :likeList="likeList"
      :likeMember="likeMember"
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
    ...mapState(["performance", "couponList"]),
  },
  mounted() {
    this.fetchPerformance(this.performNo);
    this.fetchCouponList();
    this.fetchPerformanceLike(this.performNo);
    this.checkMember()
  },
  methods: {
    ...mapActions(["fetchPerformance", "fetchCouponList", "fetchPerformanceLike"]),
     checkMember () {
          let token = localStorage.getItem('token')
          if(token != null){
            axios.get('likes/member' , { params:{token:token} })
            .then((res) => {
                for (let i = 0; i <this.likeList.length; i++){
                    console.log(res.data)

                    if(this.likeList[i].member.memberNo === res.data) {
                        return this.likeMember = true
                    }
                }
                return this.likeMember = false
            })
          }
     }
  },
};
</script>
