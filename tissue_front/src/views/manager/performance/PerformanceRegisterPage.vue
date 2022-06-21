<template>
    <div>
        <v-container>
              <h3>공연 이미지 업로드 테스트 페이지~!!!</h3>
                <form @submit.prevent="submitFiles">
                <v-text-field type="text" v-model="performName" label="공연명"></v-text-field>
                공연시작일<v-text-field type="Date" v-model="performStart"></v-text-field>
                공연마감일<v-text-field type="Date" v-model="performEnd"></v-text-field>
                <v-text-field type="Time" v-model="performTime" label="공연시간"></v-text-field>
                <v-text-field type="Number" v-model="performPriceR" label="R석가격"></v-text-field>
                <v-text-field type="Number" v-model="performPriceS" label="S석가격"></v-text-field>
                <v-text-field type="Number" v-model="performPriceVip" label="vip석가격"></v-text-field>
                <v-select v-model="performArea" label="지역" :items="areaList"></v-select>
                <v-select v-model="performCategory" label="카테고리" :items="categoryList"></v-select>
                <v-select v-model="performGrade" label="등급" :items="gradeList"></v-select>
                <v-textarea type="text" v-model="performer" label="출연진"></v-textarea>
                <v-file-input couter multiple small-chips id="file" ref="file"
                                label="썸네일이미지" v-on:change="handleFileUpload()"/>
                <div id='btn'>
                    <v-btn class="white--text" id="BtnRegister" v-on:click="submitFiles()">공연 업로드</v-btn>
                </div>
            </form>
        </v-container>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    name: 'VueFileUploadPage',
    data () {
        return {
            performName: '',
            performStart: '',
            performEnd: '',
            performTime: '',
            performPriceR: '',
            performPriceS: '',
            performPriceVip: '',
            performArea: '',
            areaList: ['서울', '경기/인천', '대전/충청/강원', '부산/대구/울산/경상', '광주/전라', '제주'],
            performCategory: '',
            categoryList: ['콘서트', '뮤지컬', '연극'],
            performGrade: '',
            gradeList: ['만 7세 이상', '만 12세 이상', '만 19세 이상'],
            performer: '',
            file: ''
        }
    },
    methods: {
        handleFileUpload () {
            this.file = this.$refs.file.files
            console.log("file upload")
        },
        submitFiles () {
            let performData = new FormData()
            performData.append("performName", this.performName)
            performData.append("performStart", this.performStart)
            performData.append("performEnd", this.performEnd)
            performData.append("performTime", this.performTime)
            performData.append("performPriceR", this.performPriceR)
            performData.append("performPriceS", this.performPriceS)
            performData.append("performPriceVip", this.performPriceVip)
            performData.append("performArea", this.performArea)
            performData.append("performCategory", this.performCategory)
            performData.append("performGrade", this.performGrade)
            performData.append("performer", this.performer)

            for (let idx = 0; idx < this.file.length; idx++) {
                performData.append("filesList", this.file[idx])
            }

            axios.post('http://localhost:7777/performance/register', performData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then (res => {
                alert('처리 결과: ' + res.data)
            })
            .catch (res => {
                alert('처리 결과: ' + res.message)
            })

        }
    }
}
</script>

<style scoped>
.background {
    background-color: rgb(238, 238, 238);
}
#btn { text-align: center; }
#BtnRegister { background-color: orange; width: 350px; height: 50px; font-size: 18px;}

</style>