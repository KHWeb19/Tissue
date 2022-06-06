<template>
    <div>

        <v-row justify="center" class="mb-3 mt-3 text-center">
            <h3><strong>공연리스트</strong></h3>
        </v-row>


        <table class="table table-bordered">
                <tr align="center">
                    <th align="center" width="100">공연번호</th>
                    <th align="center" width="240">썸네일</th>
                    <th align="center" width="200">카테고리</th>
                    <th align="center" width="640">공연명</th>
                    <th align="center" width="100">지역</th>
                    <th align="center" width="240">공연시작일자</th>
                    <th align="center" width="240">공연종료일자</th>
                    <th align="center" width="240">공연시작시간</th>
                </tr>  
                <tr v-if="!performances || (Array.isArray(performances) && performances.length === 0)">
                    <td colspan="4">
                        현재 등록된 공연이 없습니다!
                    </td>
                </tr>
                <tr v-else v-for="performance in performances" :key="performance.performNo">
                    <td align="center">
                        {{ performance.performNo }}
                    </td>
                    <td align="center">
                        <v-img :src="require(`../../assets/thumbNail/${performance.performThumbnail}`)" alt="">
                        </v-img>
                    </td>
                    <td align="center">
                        {{ performance.performCategory }}
                    </td>
                    <td align="center">
                        <router-link :to="{ name: 'PerformanceReadPage',
                                            params: { performNo: performance.performNo.toString() } }">
                            {{ performance.performName }}
                        </router-link>
                    </td>
                    <td align="center">
                        {{ performance.performArea }}
                    </td>
                    <td align="center">
                        {{ performance.performStart}}
                    </td>
                    <td align="center">
                        {{ performance.performEnd}}
                    </td>
                    <td align="center">
                        {{ performance.performTime}}
                    </td>
                </tr>
        </table>
    </div>
</template>

<script>


export default {
    name: 'PerformanceListForm',
    props: {
      performances: {
            type: Array
        }
    }
}

</script>