# mirrg.helium.swing

- Repo: `https://raw.githubusercontent.com/MirrgieRiana/mirrg.helium.swing/master/maven`
- Artifacts
  - `mirrg:mirrg.helium.swing.nitrogen.util:+`
  - `mirrg:mirrg.helium.swing.nitrogen.wrapper:+`
  - `mirrg:mirrg.helium.swing.nitrogen.applet:+`

## mirrg.helium.swing.nitrogen.util

### 説明

AWT・Swing関連の単純な物をまとめたプロジェクト

### エントリーポイント

- `mirrg.helium.swing.nitrogen.util.*`

### 機能

- GroupBuilder
  - 階層的構造を持つGroupLayoutが簡単に組めるユーティリティ
- HSwing
  - LookAndFeel関連メソッド
  - インラインでSliptPaneなどの構造物を作れる関数
  - インラインでプロパティを設定できる関数
  - インラインでイベントの一つのメソッドを設定できる関数
- NamedSlot
  - JList用にtoStringを偽装できるクラス
- TitledGroup
  - タイトル付き枠を表すコンポーネント

## mirrg.helium.swing.nitrogen.wrapper

### 説明

Swing製のアーティファクトをまとめウィンドウのイベントを抽出するプロジェクト

### エントリーポイント

- `mirrg.helium.swing.nitrogen.wrapper.FrameNitrogen`
- `mirrg.helium.swing.nitrogen.wrapper.artifacts.*`

### 機能

- FrameNitrogen
  - ウィンドウの生成・破棄・表示・非表示のイベントを抽出して提供する。
- artifacts
  - ログを表示するウィンドウ
  - HTMLを表示できるウィンドウ
  - LookAndFeelを変更できるメニュー

## mirrg.helium.swing.nitrogen.applet

### 説明

高機能アプレットクラス

### エントリーポイント

- `mirrg.helium.swing.nitrogen.applet.AppletNitrogen`

### 機能

- AppletNitrogen
  - アプレット拡張用の素体
- HAppletNitrogen#applyStandard
  - ゲーム用スレッド
  - FPS調整器
  - スレッドセーフ化した3重バッファ
  - マウスとキーボード入力用ユーティリティ
