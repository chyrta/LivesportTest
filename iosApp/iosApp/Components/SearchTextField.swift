//
//  SearchTextField.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 19.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SearchTextField: View {

    let title: String
    var onChangeQuery: (String) -> Void
    var onClearClick: () -> Void
    @Binding var text: String
    @State var isEditing: Bool = false

    private var isClear: Bool {
        return self.isEditing && !text.isEmpty
    }

    var body: some View {
        ZStack(alignment: .trailing) {
            TextField(
                self.title,
                text: self.$text) { isEditing in
                self.isEditing = isEditing
            } onCommit: {
                self.isEditing = false
            }.onChange(of: text, perform: { onChangeQuery($0) })
                .padding(.trailing, self.isClear ? 18 : 0)

            if self.isClear {
                Button {
                    self.text = ""
                    onClearClick()
                } label: {
                    Image(systemName: "multiply.circle.fill").foregroundColor(.secondary)
                }
                    .buttonStyle(PlainButtonStyle())
            }
        }.frame(alignment: .trailing)
    }

}
