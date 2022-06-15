<template>
    <div class="board-list mt-15">
        <table class="w3-table-all">
            <thead>
                <tr align="center">
                    <th align="center" width="150">공연번호</th>
                    <th align="center" width="240">썸네일</th>
                    <th align="center" width="200">카테고리</th>
                    <th align="center" width="640">공연명</th>
                    <th align="center" width="100">지역</th>
                    <th align="center" width="240">공연시작일자</th>
                    <th align="center" width="240">공연종료일자</th>
                    <th align="center" width="240">공연시작시간</th>
                </tr> 
            </thead> 
            <tbody>
                <tr v-if="!performances || (Array.isArray(performances) && performances.length === 0)">
                    <td colspan="8">
                        현재 등록된 공연이 없습니다!
                    </td>
                </tr>
                        <tr v-else v-for="performance in performances" :key="performance.performNo">
                            <td align="center">
                                {{ performance.performNo }}
                            </td>
                            <td align="center">
                                <img :src="require(`../../assets/thumbNail/${performance.performThumbnail}`)" alt=""/>
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
                    </tbody>
        </table>
        <div>
            <v-btn absolute right plain router-link :to="{ name: 'PerformanceRegisterPage' }">
                <v-icon>mdi-clipboard-edit-outline</v-icon>
            </v-btn>
        </div>  
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

<style scoped>
.background {
    background-color: rgb(241, 241, 241);
}
table {
  width: 100%;
  border-collapse: collapse;
}
table th {
  font-size: 1rem;
}
table tr {
  height: 0.5rem;
  text-align: center;
  border-bottom: 1px solid #505050;
}
table tr:first-of-type {
  border-top: 2px solid #404040;
}
table tr td {
  padding: 1rem 0;
  font-size: 1rem;
}
img {
    width: 100px;
    height: 100px;
    border-radius: 70%;
    overflow: hidden;
    object-fit: cover;
}
.board-list {
    width: 100%;
    margin: auto;
}  
</style>>