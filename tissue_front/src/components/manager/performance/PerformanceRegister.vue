<template>
    <div>
        <v-container>
            <h3>공연 이미지 업로드 테스트 페이지~</h3>
            <form @submit.prevent="onSubmit">
            <div>
                <v-input type="text" v-model="performName">공연명</v-input>
                <v-input type="Date" v-model="performStart" label="공연시작일"></v-input>
                <v-input type="Date" v-model="performEnd" label="공연마감일"></v-input>
                <v-input type="Time" v-model="performTime" label="공연시간"></v-input>
                <v-text-field type="Number" v-model="performPriceR" label="R석가격"></v-text-field>
                <v-text-field type="Number" v-model="performPriceS" label="S석가격"></v-text-field>
                <v-text-field type="Number" v-model="performPriceVip" label="vip석가격"></v-text-field>
                <v-text-field type="text" v-model="performArea" label="지역"></v-text-field>
                <v-text-field type="text" v-model="performCategory" label="공연카테고리"></v-text-field>
                <v-text-field type="text" v-model="performGrade" label="등급"></v-text-field>
                <v-text-field type="text" v-model="performer" label="퍼포머"></v-text-field>

                <label>Files
                    <input type="file" id="files" ref="file"
                            multiple v-on:change="handleFileUpload()"/>
                </label>
                <button v-on:click="submitFiles()">파일 업로드</button>
            </div>
            <div>
                <label>Files
                    <v-input type="file" id="files2" ref="files2"
                           multiple v-on:change="handleFileUpload2()"/>
                </label>
                <button v-on:click="submitFiles2()">파일 + 문자열 전송</button>
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
            performCategory: '',
            performGrade: '',
            performer: '',
            file: ''
        }
    },
    methods: {
        onSubmit () {
            const { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
                performPriceVip, performArea, performCategory, performGrade, performer, file } = this
            this.$emit('submit', { performName, performStart, performEnd, performTime, performPriceR, performPriceS,
            performPriceVip, performArea, performCategory, performGrade, performer, file })
        }
    }
}
</script>