package com.tambi.convention

/**
 * ## App Version Config Class
 *
 * ### Updated at 2023.07.07
 *
 * ### Updated by JaeyeonG1
 *
 * ```
 * /* Generated Version Code Sample */
 * val ProdVersionCode = "1.0.0" // Major.Minor.Patch
 * val StagingVersionCode = "1.0.0-stg.1" // Major.Minor.Patch-stg.stagingVersion
 * val DevelopVersionCode = "1.0.0-dev.1" // Major.Minor.Patch-stg.developVersion
 * ```
 * @property MajorVersion API 대규모 변경 등의 이유로 하위 버전과 호환 되지 않는 변경
 * @property MinorVersion 하위 버전과 호환 되며, 새로운 기능이 추가 되는 변경
 * @property PatchVersion 버그 수정, 서버 내부 코드 수정 등 사용자 인지 어려운 변경
 * @property StagingVersion Staging Suffix Number. 추가 QA 등 Staging 환경 에서의 내부 배포 시 상향
 * @property DevelopVersion Dev Suffix Number. 개발 단계의 기능 추가 시 상향
 */
internal object Versions {
    const val MajorVersion = 0
    const val MinorVersion = 0
    const val PatchVersion = 1

    const val StagingVersion = 1 // Release Candidate Version
    const val DevelopVersion = 1 // Alpha Version
}
