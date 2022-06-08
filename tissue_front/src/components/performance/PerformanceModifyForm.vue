<template>
    <div>
        <form @submit.prevent="onSubmit">
        <v-container fluid>
            <v-row>
                <v-col sm="3"><label>공연번호</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performNo" disabled/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연명</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performName"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연시작일</label></v-col>
                <v-col sm="6">
                    <input type="Date" :value="performance.performStart"/>
                </v-col>
            </v-row>
        
            <v-row >
                <v-col sm="3"><label>공연종료일</label></v-col>
                <v-col sm="6">
                    <input type="Date" :value="performance.performEnd"/>
                </v-col>
            </v-row>
            
            <v-row >
                <v-col sm="3"><label>공연시간</label></v-col>
                <v-col sm="6">
                    <input type="Time" :value="performance.performTime"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>R석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceR"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>S석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceS"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>vip석가격</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performPriceVip"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>지역</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performArea"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>공연카테고리</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performCategory"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>등급</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performGrade"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>출연자</label></v-col>
                <v-col sm="6">
                    <input type="text" :value="performance.performer"/>
                </v-col>
            </v-row>

            <v-row >
                <v-col sm="3"><label>썸네일</label></v-col>
                <v-col sm="6">
                   <input type="file" ref="file" id="file" @change="handleThumbNailUpload()"/>
                </v-col>
                <div>
                    <img :src=file.preview class="preview"/>
                </div>
            </v-row>

            <v-row >
                <v-col sm="3"><label>상세이미지</label></v-col>
                <v-col sm="6">
                   <input type="file" ref="files" multiple @change="handleFileUpload()"/>
                </v-col>
                <div v-for="(file, index) in files" :key="index" style="text-align:center">
                    <img :src=file.preview class="preview"/>
                </div>
            </v-row>
    
        </v-container>

        <div>
            <v-btn plain type="submit">수정 완료</v-btn>
            <v-btn plain router-link :to="{ name: 'PerformanceReadPage',
                params: { performNo: performance.performNo.toString() } }">
                취소
            </v-btn>
        </div>
        </form>
    </div>
</template>

<script>

export default {
    name: 'PerformanceModifyForm',
    props: {
        performance: {
            type: Object,
            required: true
        }
    },
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
            performCategory: '',
            performGrade: '',
            performer: '',
            files: '', // 다중이미지
            file: '', // 썸네일
        }
    },
    methods: {
        handleThumbNailUpload () {
            this.file = this.$refs.file.files
            console.log(this.file)

            this.file = {
                file: this.$refs.file.files[0],
                preview: URL.createObjectURL(this.$refs.file.files[0])
            }
        },
       handleFileUpload () {
            this.files = this.$refs.files.files
            console.log(this.files)

             for (let i = 0; i < this.$refs.files.files.length; i++){
                this.files = [
                    ...this.files,
                    {
                        file: this.$refs.files.files[i],
                        preview: URL.createObjectURL(this.$refs.files.files[i])
                    
                    }
                ]
            }
        },
        onSubmit () {
            const { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
                    performPriceVip, performArea, performCategory, performGrade, performer} = this

            let formData = new FormData()

            formData.append('performName', performName)
            formData.append('performStart', performStart)
            formData.append('performEnd', performEnd)
            formData.append('performTime', performTime)
            formData.append('performPriceR', performPriceR)
            formData.append('performPriceS', performPriceS)
            formData.append('performPriceVip', performPriceVip)
            formData.append('performArea', performArea)
            formData.append('performCategory', performCategory)
            formData.append('performGrade', performGrade)
            formData.append('performer', performer)

            formData.append('file', document.getElementById('file').files[0])
        
            for (let idx = 0; idx < this.files.length; idx++) {
                formData.append('fileList', this.files[idx])
            }
            
            this.$emit('submit', { formData })
        }
    },
    created () {
        this.performName = this.performance.performName
        this.performStart = this.performance.performStart
        this.performEnd = this.performance.performEnd
        this.performTime = this.performance.performTime
        this.performPriceR = this.performance.performPriceR
        this.performPriceS = this.performance.performPriceS
        this.performPriceVip = this.performance.performPriceVip
        this.performArea = this.performance.performArea
        this.performCategory = this.performance.performCategory
        this.performer = this.performance.performer
        // this.file = this.performance.performDetailImg
        // this.files = this.performance.performThumbnail
    }
}
</script>
