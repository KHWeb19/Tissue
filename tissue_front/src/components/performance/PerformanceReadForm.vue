<template>
    <div>
        <v-container fluid>
            <v-row>
                <v-col sm="3"><label>공연번호</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performNo" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연명</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performName" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연시작일</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performStart" readonly/>
                </v-col>
            </v-row>
        
            <v-row >
                <v-col sm="3"><label>공연종료일</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performEnd" readonly/>
                </v-col>
            </v-row>
            
            <v-row >
                <v-col sm="3"><label>공연시간</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performTime" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>R석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceR" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>S석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceS" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>vip석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceVip" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>지역</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performArea" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연카테고리</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performCategory" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>등급</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performGrade" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>출연자</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performer" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>썸네일</label></v-col>
                <v-col sm="6">
                    <v-img :src="require(`../../assets/thumbNail/${performance.performThumbnail}`)" readonly/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>상세이미지</label></v-col>
                <v-col sm="6">
                    <v-img :src="require(`../../assets/detailImg/${performance.performDetailImg}`)" readonly/>
                </v-col>
            </v-row>
    
        </v-container>
    </div>
</template>

<script>

export default {
    name: 'PerformanceReadForm',
    props: {
        performance: {
            type: Object,
            required: true
        }
    }
}
</script>