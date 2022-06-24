<template>
    <v-container class="board-list mt-5">
        <v-app-bar app elevation="3">
            <v-toolbar-title class="ml-3">
                📌 공연 관리
            </v-toolbar-title>
        </v-app-bar>
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
                                <router-link :to="{ name: 'PerformanceReadPage',
                                                    params: { performNo: performance.performNo.toString() } }">
                                    <img :src="require(`../../assets/thumbNail/${performance.performThumbnail}`)" alt=""/>
                                </router-link>
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
            <v-btn plain router-link :to="{ name: 'PerformanceRegisterPage' }">
                <v-icon>mdi-clipboard-edit-outline</v-icon>
            </v-btn>  

    </v-container>
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
  border: 1px #a39485 solid;
  font-size: .9em;
  box-shadow: 0 2px 5px rgba(0,0,0,.25);
  width: 100%;
  border-collapse: collapse;
  border-radius: 5px;
  overflow: hidden;
}

thead {
  font-weight: bold;
  color: black;
  background: rgb(195, 233, 248);
}

td, th {
  padding: 1em .5em;
  vertical-align: middle;
}

td {
  border-bottom: 1px solid rgba(0,0,0,.1);
  background: #fff;
}

img {
    width: 100px;
    height: 100px;
    border-radius: 70%;
    overflow: hidden;
    object-fit: cover;
}
/* .board-list {
    width: 100%;
    margin: auto;
}   */
</style>>